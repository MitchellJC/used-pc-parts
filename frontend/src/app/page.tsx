import Home from "./Home";

const BACKEND_DOMAIN = "http://127.0.0.1:8080";

async function getNewListings() {
  return fetch(BACKEND_DOMAIN + "/listing/new", {
    method: "GET",
  }).then((response) => response.json());
}

export default async function Page(): Promise<JSX.Element> {
  const newListings = await getNewListings();
  return <Home newListings={newListings}></Home>;
}
