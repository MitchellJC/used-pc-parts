function NavBar(): JSX.Element {
  return (
    <div>
      <header className="flex gap-2 bg-slate-900 p-3">
        <span className="text-xl mr-3">UsedPCParts.com</span>
        <a href="/" className="my-auto">
          Home
        </a>
        <a href="/login" className="my-auto">
          Login
        </a>
      </header>
    </div>
  );
}

export default NavBar;
