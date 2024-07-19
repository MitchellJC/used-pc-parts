function LoginPage(): JSX.Element {
  return (
    <div className="mt-10 mx-auto p-3 w-96 max-w-full bg-slate-800">
      <h2 className=" text-center">Please Login</h2>
      <LoginForm></LoginForm>
    </div>
  );
}

function LoginForm() {
  let csrfCookie: string;
  const loginLabelClass: string = "mt-2 w-prose max-w-full w-prose";
  const loginInputClass: string = "text-black w-prose max-w-full p-1";

  // // Get CSRF cookie
  // let csrfResponse: Response = await fetch("http://127.0.0.1:8080/user/login", {
  //   method: "GET",
  // });

  // let cookies = csrfResponse.headers.getSetCookie().toString().split(";");
  // console.log(cookies);

  // // Find CSRF cookie
  // for (const cookie of cookies) {
  //   let [cookieName, cookieVal] = cookie.split("=");
  //   if (cookieName == "XSRF-TOKEN") {
  //     csrfCookie = cookieVal;
  //     break;
  //   }
  // }

  // function login() {
  //   // "use server";
  //   console.log("Starting login");
  //   await fetch("http://127.0.0.1:8080/user/login", {
  //     method: "POST",
  //     body: JSON.stringify({
  //       username: "admin@email.com",
  //       password: "password",
  //     }),
  //   })
  //     .then((response) => response.text())
  //     .then((json) => console.log(json));
  // }

  return (
    <form className="flex flex-col">
      <label htmlFor="email" className={loginLabelClass}>
        Email:
      </label>
      <input
        type="email"
        id="email"
        name="email"
        className={loginInputClass}
      ></input>
      <label htmlFor="password" className={loginLabelClass}>
        Password:
      </label>
      <input
        type="password"
        id="password"
        name="password"
        className={loginInputClass}
      ></input>
      <input
        type="submit"
        value="Login"
        className="border-solid border-white border-2 mt-3 bg-teal-800 focus:bg-teal-900 cursor-pointer"
      ></input>
    </form>
  );
}

export default LoginPage;
