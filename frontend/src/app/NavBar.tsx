import { MouseEventHandler } from "react";

function NavBar({
  goHome,
  goLogin,
}: {
  goHome: MouseEventHandler;
  goLogin: MouseEventHandler;
}): JSX.Element {
  return (
    <div>
      <header className="flex gap-2 bg-red-900 p-1">
        <button onClick={goHome}>Home</button>
        <button onClick={goLogin}>Login</button>
      </header>
    </div>
  );
}

export default NavBar;
