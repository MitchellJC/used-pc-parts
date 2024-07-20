"use client";

import DisplayCase from "./DisplayCase";
import ListingData from "./ListingData";

function Home({ newListings }: { newListings: ListingData[] }) {
  return (
    <div className="bg-slate-800 p-4">
      <h1 className="text-5xl">Home</h1>
      <DisplayCase listings={newListings}></DisplayCase>
    </div>
  );
}

export default Home;
