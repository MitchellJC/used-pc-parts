import DisplayCase from "./DisplayCase";

function Home() {
  return (
    <div className="bg-slate-800 p-4">
      <h1 className="text-5xl">Home</h1>
      <DisplayCase
        listings={[
          { id: 1, name: "potato", description: "tuber", price: 20 },
          { id: 2, name: "apple", description: "fruit", price: 1 },
        ]}
      ></DisplayCase>
    </div>
  );
}

export default Home;
