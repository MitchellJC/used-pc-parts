interface Props {
  firstName?: string;
}

function NavBar({ firstName }: Props): JSX.Element {
  let greeting: JSX.Element;

  if (typeof firstName !== "undefined") {
    greeting = <span>Hi, {firstName}</span>;
  } else {
    greeting = <span></span>;
  }
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
        {greeting}
      </header>
    </div>
  );
}

export default NavBar;
