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
      <header className="flex gap-2 bg-slate-900 p-3">
        <span className="text-xl mr-3">UsedPCParts.com</span>
        <button onClick={goHome}>Home</button>
        <button onClick={goLogin}>Login</button>
      </header>
    </div>
  );
}

export default NavBar;
