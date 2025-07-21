document.addEventListener("DOMContentLoaded", () => {
  const USUARIO_ID = 5;

  // === Cargar productos desde backend ===
  fetch("http://localhost:8080/api/productos")
    .then(response => {
      if (!response.ok) throw new Error("API NinfaGarden no disponible");
      return response.json();
    })
    .then(data => {
      if (Array.isArray(data)) {
        cargarProductos(data);
      } else {
        throw new Error("Formato inesperado de la API");
      }
    })
    .catch(error =>
      console.error("Error al obtener productos desde la API", error)
    );

  function cargarProductos(data) {
    const productosContainer = document.getElementById("productos-container");
    productosContainer.innerHTML = "";

    data.forEach(producto => {
      const shortDescription = producto.descripcion.split(" ").slice(0, 5).join(" ") + "...";
      productosContainer.innerHTML += `
        <div class="card">
          <img src="${producto.imagenUrl}" class="card-img-top" alt="${producto.nombre}">
          <div class="card-body">
            <h5 class="card-title">${producto.nombre}</h5>
            <p class="card-text short-description">${shortDescription}</p>
            <p class="card-text full-description" style="display: none;">
              ${producto.descripcion}
            </p>
            <button class="btn btn-link" onclick="toggleDescription(this)">
              Ver descripción
            </button>
            <p class="card-text">$${producto.precio.toFixed(2)}</p>
            <button class="btn btn-primary" onclick="addToCart(
              ${producto.id}, '${producto.imagenUrl}', '${producto.nombre}',
              ${producto.precio}, this)">Agregar al carrito</button>
          </div>
        </div>
      `;
    });
  }

  // === Toggle descripción ===
  window.toggleDescription = function (button) {
    const shortDescription = button.previousElementSibling;
    const fullDescription = shortDescription.nextElementSibling;
    if (fullDescription.style.display === "none") {
      fullDescription.style.display = "block";
      shortDescription.style.display = "none";
      button.textContent = "Ocultar descripción";
    } else {
      fullDescription.style.display = "none";
      shortDescription.style.display = "block";
      button.textContent = "Ver descripción";
    }
  };

  // === Añadir al carrito ===
  window.addToCart = function (id, image, title, price, button) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    let existingProduct = cart.find(product => product.id === id);
    if (existingProduct) {
      existingProduct.quantity++;
    } else {
      cart.push({ id, image, title, price, quantity: 1 });
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    updateCartUI();
    button.textContent = "Agregado";
    button.disabled = true;
    setTimeout(() => {
      button.textContent = "Agregar al carrito";
      button.disabled = false;
    }, 1000);
  };

  // === Actualizar carrito ===
  function updateCartUI() {
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    const carritoItems = document.getElementById("carrito-items");
    carritoItems.innerHTML = "";
    let total = 0;

    if (cart.length === 0) {
      carritoItems.innerHTML = '<li class="text-center text-muted w-100">Tu carrito está vacío</li>';
      document.getElementById("realizar-compra").disabled = true;
    } else {
      document.getElementById("realizar-compra").disabled = false;

      cart.forEach(item => {
        const cartItemHTML = `
          <li class="cart-item">
            <img src="${item.image}" alt="${item.title}">
            <div class="card-body">
              <h6 class="card-title">${item.title}</h6>
              <p class="card-text">Cantidad: <strong>${item.quantity}</strong></p>
              <p class="card-text">Precio unitario: $${item.price}</p>
              <p class="card-text">Subtotal: <strong>$${(item.price * item.quantity).toFixed(2)}</strong></p>
              <button class="btn btn-sm btn-danger" onclick="removeFromCart(${item.id})">
                Eliminar
              </button>
            </div>
          </li>
        `;
        carritoItems.innerHTML += cartItemHTML;
        total += item.price * item.quantity;
      });
    }

    document.getElementById("carrito-total").textContent = total.toFixed(2);
    document.getElementById("cart-counter").textContent = cart.reduce(
      (sum, item) => sum + item.quantity, 0
    );
    document.getElementById("cart-counter-nav").textContent = cart.reduce(
      (sum, item) => sum + item.quantity, 0
    );
  }

  window.removeFromCart = function (id) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart = cart.filter(item => item.id !== id);
    localStorage.setItem("cart", JSON.stringify(cart));
    updateCartUI();
  };

  document.getElementById("vaciar-carrito").addEventListener("click", () => {
    localStorage.clear();
    updateCartUI();
  });

  document.getElementById("realizar-compra").addEventListener("click", () => {
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
    if (cart.length === 0) {
      alert("Tu carrito está vacío");
      return;
    }

    const pedido = {
      usuarioId: USUARIO_ID,
      items: cart.map(item => ({
        productoId: item.id,
        cantidad: item.quantity
      }))
    };

    fetch("http://localhost:8080/api/pedidos", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(pedido)
    })
      .then(response => {
        if (!response.ok) {
          return response.json().then(error => {
            throw new Error(error.message || "Error al procesar el pedido");
          });
        }
        return response.json();
      })
      .then(data => {
        console.log("Pedido exitoso:", data);

        const total = cart.reduce(
          (sum, item) => sum + item.price * item.quantity,
          0
        );
        document.getElementById("modal-total").textContent = total.toFixed(2);

        const modal = new bootstrap.Modal(
          document.getElementById("compraExitosaModal")
        );
        modal.show();

        localStorage.clear();
        updateCartUI();
      })
      .catch(error => {
        console.error("Error en el pedido:", error);
        alert(error.message);
      });
  });

  document.getElementById("btn-guardar-producto").addEventListener("click", (e) => {
  e.preventDefault();

  const nombre = document.getElementById("prod-nombre").value.trim();
  const descripcion = document.getElementById("prod-descripcion").value.trim();
  const precio = parseFloat(document.getElementById("prod-precio").value);
  const stock = parseInt(document.getElementById("prod-stock").value);
  const imagenUrl = document.getElementById("prod-imagen").value.trim();
  const tipo = document.getElementById("prod-categoria").value;

  const payload = {
    nombre: nombre,
    descripcion: descripcion,
    precio: precio,
    stock: stock,
    imagenUrl: imagenUrl,
    tipo: tipo
  };

  if (tipo === "Planta") {
    payload.climaRecomendado = document.getElementById("prod-clima").value.trim();
  } else if (tipo === "Herramienta") {
    payload.material = document.getElementById("prod-material").value.trim();
  }

  fetch("http://localhost:8080/api/productos", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
    .then((res) => {
      if (!res.ok) throw new Error("Error al guardar");
      return res.json();
    })
    .then((data) => {
      alert("Guardado OK - Recarga la página para ver el nuevo producto");
      console.log(data);

      // === Agregar dinámicamente la nueva card ===
      const contenedor = document.getElementById("productos-container");

      const nuevaCard = document.createElement("div");
      nuevaCard.className = "card";
      nuevaCard.innerHTML = `
        <img src="${data.imagenUrl}" alt="${data.nombre}"/>
        <h5>${data.nombre}</h5>
        <p>${data.descripcion}</p>
        <button class="btn">Añadir al carrito</button>
      `;

      contenedor.appendChild(nuevaCard);

      document.getElementById("form-agregar-producto").reset();
      document.getElementById("campo-clima").style.display = "none";
      document.getElementById("campo-material").style.display = "none";
    })
    .catch((err) => alert(err.message));
});



// === Ver historial de pedidos ===
document.getElementById("ver-pedidos").addEventListener("click", () => {
  fetch(`http://localhost:8080/api/pedidos/usuario/${USUARIO_ID}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("No se pudo obtener el historial de pedidos");
      }
      return response.json();
    })
    .then(data => mostrarPedidos(data))
    .catch(error => {
      console.error(error);
      alert("Error al cargar historial de pedidos");
    });
});

function mostrarPedidos(pedidos) {
  const contenedor = document.getElementById("pedidos-container");
  if (!pedidos || pedidos.length === 0) {
    contenedor.innerHTML = "<p class='text-center'>No tienes pedidos registrados.</p>";
    return;
  }

  let tabla = `
    <table class="table table-bordered">
      <thead>
        <tr>
          <th># Pedido</th>
          <th>Estado</th>
          <th>Total</th>
          <th>Productos</th>
        </tr>
      </thead>
      <tbody>
  `;

  pedidos.forEach(p => {
    const prods = p.items.map(item => `${item.nombreProducto} x${item.cantidad}`).join(", ");
    tabla += `
      <tr>
        <td>${p.id}</td>
        <td>${p.estado}</td>
        <td>$${p.total.toFixed(2)}</td>
        <td>${prods}</td>
      </tr>
    `;
  });

  tabla += "</tbody></table>";
  contenedor.innerHTML = tabla;
}


  // === Cerrar sesión ===
  document.getElementById("cerrar-sesion").addEventListener("click", () => {
    fetch(`http://localhost:8080/api/pedidos/usuario/${USUARIO_ID}`, {
      method: 'DELETE'
    })
    .then(() => {
      localStorage.clear();
      document.getElementById("pedidos-container").innerHTML = "";
      alert("Sesión finalizada y pedidos eliminados");
      location.reload();
    })
    .catch(error => {
      console.error("Error al borrar pedidos:", error);
      localStorage.clear();
      location.reload();
    });
  });

  // === Inicializar UI carrito ===
  updateCartUI();
});
