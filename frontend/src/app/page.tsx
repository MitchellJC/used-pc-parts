import Home from "./Home";
import { BACKEND_DOMAIN } from "./config";

async function getNewListings() {
  return fetch(BACKEND_DOMAIN + "/listing/new", {
    method: "GET",
  }).then((response) => response.json());
}

export default async function Page(): Promise<JSX.Element> {
  const newListings = await getNewListings();
  return <Home newListings={newListings}></Home>;
}
