# Sistema de Gestión para Concesionaria de Vehículos

## Descripción General

Los clientes de la concesionaria podrán realizar pedidos de compra de vehículos a través del sistema.
El proceso de compra abarcará distintas áreas de la concesionaria, incluyendo ventas, cobranzas,
impuestos, embarque, logística, entrega y seguimiento.

Los clientes podrán seleccionar el vehículo de su interés desde un catálogo en línea,
configurando características como modelo, color y equipamiento adicional. Una vez realizado el
pedido, se generará una orden de compra y se notificará a las áreas correspondientes para su
procesamiento.

El sistema deberá contemplar distintas formas de pago y aplicar los impuestos correspondientes
según la normativa vigente.

La entrega del vehículo podrá realizarse en la concesionaria o mediante un servicio de envío.
Una vez entregado, el cliente podrá realizar un seguimiento de su vehículo y recibir
notificaciones sobre el estado del proceso.

## Alcance y Requerimientos

El sistema deberá permitir:

- Registrar nuevos clientes y gestionar su información de contacto, incluyendo nombre, apellido,
  documento, correo electrónico y teléfono.
- Evitar la duplicación de clientes, vehículos y pedidos de compra. En caso de detectar un
  duplicado, el sistema deberá emitir una excepción con un mensaje adecuado.
- Manejar errores mediante excepciones. Si ocurre un problema en el procesamiento de un
  pedido, en la validación de datos o en cualquier otra operación crítica, el sistema deberá
  generar excepciones para informar el problema y evitar inconsistencias en los datos.
- Cargar el catálogo de vehículos disponibles, incluyendo características y precios.
- Registrar los datos del vehículo en cada pedido, incluyendo marca, modelo, color, número de
  chasis y número de motor.
- Realizar pedidos de compra de vehículos con opciones de personalización.
- Gestionar las distintas etapas del proceso de compra, utilizando "marcas" que representen el
  avance del pedido a través de las siguientes áreas: ventas, cobranzas, impuestos, embarque,
  logística y entrega.
- Mantener un historial de cambios de estado en los pedidos de compra.
- Notificar a las áreas de la concesionaria sobre cambios en el estado de los pedidos.
- Registrar los datos del pedido de compra, incluyendo: número de pedido, fecha de creación,
  datos del cliente (nombre, apellido, documento, correo electrónico, teléfono), datos del
  vehículo (marca, modelo, color, número de chasis, número de motor), configuraciones
  adicionales (equipamiento extra, garantías extendidas, accesorios), forma de pago (contado,
  transferencia o tarjeta), gestión de impuestos según el tipo de vehículo. Costo total (precio
  base + impuestos + adicionales), datos de facturación (nombre o razón social, dirección,
  CUIT/CUIL si aplica), datos del vendedor (nombre, correo electrónico), área responsable
  actual (ejemplo: ventas, cobranzas, embarque, etc.),
- Historial de cambios de estado en el pedido.
- El nombre de la concesionaria y numero de CUIT deberá aparecer en todos los informes y
  pedidos.
- Generar informes de pedidos de compra. El sistema deberá permitir la generación de reportes
  que muestren los pedidos de compra registrados, con la opción de filtrar por fecha y estado.
  Estos informes deben incluir los detalles relevantes de cada pedido y ser exportables en
  formatos adecuados para su análisis.

## Cálculo de Impuestos y Forma de Pago

Para realizar el cálculo del precio final de cada vehículo, se deberá aplicar la siguiente lógica de
impuestos, que variará según el tipo de vehículo:

- Impuesto Nacional:
  - Autos: 21%
  - Camionetas: 10%
  - Motos y camiones: No aplicable
- Impuesto Provincial General: 5% (aplica a todos los vehículos)
- Impuesto Provincial Adicional:
  - Camiones y Camionetas: 2%
  - Autos y Motos: 1%

La implementación del cálculo de impuestos deberá realizarse, permitiendo que cada tipo de
vehículo defina su propio plan de cálculo de impuestos sin afectar la lógica de otros vehículos.

Además, el sistema deberá registrar la forma de pago utilizada en cada pedido de compra, que
podrá ser: contado, transferencia, tarjeta de crédito

La implementación de la forma de pago deberá hacer uso de herencia y polimorfismo,
permitiendo la posibilidad de ampliar las opciones de pago en el futuro sin modificar la
estructura del código existente.

Generar un informe con totales de precios con o sin importe y la forma de pago.

## Gestión de Vistas y Roles de Usuario

El sistema deberá contar con tres tipos de vistas según el perfil del usuario:

- Administrador: Tiene acceso a todas las funcionalidades del sistema. Puede ver y gestionar
  clientes, vehículos y pedidos de compra en todas sus etapas. Puede generar informes y
  administrar la configuración general del sistema.
- Comprador: Solo podrá ver el estado de sus propios pedidos de compra. Tendrá acceso a la lista
  de vehículos disponibles, excluyendo aquellos que ya estén en proceso de venta.
- Vendedor: Podrá ver la lista de vehículos disponibles, pero no verá aquellos que ya están en
  proceso de venta. No podrá acceder a información de los pedidos de compra ni a datos de los
  clientes.

## Consideraciones sobre Diseño

El sistema deberá estar diseñado para garantizar su flexibilidad y mantenibilidad a lo largo del
tiempo. Para ello, se aplicarán enfoques que permitan desacoplar componentes, gestionar de
manera eficiente la creación de objetos, mantener un único punto de acceso a ciertos recursos,
manejar diferentes estrategias de cálculo y proporcionar una interfaz simplificada para los
usuarios del sistema.
