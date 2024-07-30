import LoginPage from "./LoginPage";
import { BACKEND_DOMAIN } from "../config";

async function getCsrf() {}

async function submitLogin(username: string, password: string) {
  "use server";
  console.log("submitLogin");
  const response = await fetch(BACKEND_DOMAIN + "/user/login", {
    method: "POST",
    body: JSON.stringify({ username: username, password: password }),
  });
  console.log(response);
}

export default async function Page() {
  return (
    <div className="flex flex-col">
      <LoginPage submitLogin={submitLogin}></LoginPage>
    </div>
  );
}
