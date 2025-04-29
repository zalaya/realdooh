# Acceptance Test-Driven Development

- [ ] Dado un email y password válidos, cuando hago POST a /api/auth/login, recibo un token JWT en la respuesta.
- [ ] Dado un email o password inválidos, cuando hago POST a /api/auth/login, recibo un error 401 con un mensaje de error claro.
- [ ] El token JWT tiene un tiempo de expiración configurable.
- [ ] El token JWT puede ser validado correctamente.
- [ ] Al hacer GET a /api/auth/sso, el servidor devuelve una respuesta 302 (redirección) a una URL de un proveedor SSO simulado.
- [ ] Después de la redirección, el proveedor simulado devuelve un parámetro code a la ruta /api/auth/sso/callback.
- [ ] Si el code es válido, el callback genera un token JWT como si fuera un login exitoso.
- [ ] Si el code es inválido o falta, el callback devuelve error 400 o 401.