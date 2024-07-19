import DisplayCase from "./DisplayCase";

function Home() {
  return (
    <div>
      <h1 className="text-5xl">Home</h1>
      <DisplayCase
        listings={["hello", "world", "potato", "apple"]}
      ></DisplayCase>
    </div>
  );
}

export default Home;
