# Backend

## Running the application for testing

To start up the server.

```bash
./mvnw spring-boot:run
```

## Simulating user registration with curl

To simulate visiting registration page for the first time.

```bash
curl -c cookies.txt -b cookies.txt http://localhost:8080/user/register
```

The `-c` option writes cookies to a file and the `-b` option reads cookies from a file and sends in a post request.

To simulate posting a registration.

```bash
curl -c cookies.txt -b cookies.txt \
-H "X-XSRF-TOKEN: <>" \
-d "firstName=first&lastName=last&email=firstlast@email.com&password=password" \
 http://localhost:8080/user/register
```