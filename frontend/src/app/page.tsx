"use client";

import { Dispatch, SetStateAction, useState } from "react";
import Head from "next/head";
import NavBar from "./NavBar";
import Home from "./Home";
import LoginPage from "./LoginPage";
import Footer from "./Footer";

enum Page {
  HOME,
  LOGIN,
  REGISTER,
}

/**
 *
 * @param page Type of page to retrieve content for.
 * @returns Page content
 */
function getPageContent(page: Page): JSX.Element {
  if (page.valueOf() === Page.HOME.valueOf()) {
    return <Home></Home>;
  } else if (page.valueOf() === Page.LOGIN.valueOf()) {
    return (
      <div className="flex flex-col">
        <LoginPage></LoginPage>
      </div>
    );
  } else if (page.valueOf() === Page.REGISTER.valueOf()) {
    return <div></div>;
  } else {
    return <div></div>;
  }
}

export default function App() {
  const [page, setPage]: [page: Page, setPage: CallableFunction] = useState(
    Page.HOME
  );
  const content: JSX.Element = getPageContent(page);

  return (
    <div className="min-h-screen flex flex-col space justify-between">
      <Head>
        <title>hello</title>
      </Head>
      <NavBar
        goHome={() => setPage(Page.HOME)}
        goLogin={() => setPage(Page.LOGIN)}
      ></NavBar>
      {content}
      <Footer></Footer>
    </div>
  );
}
