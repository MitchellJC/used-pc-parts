import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { BACKEND_DOMAIN } from "./config";
import NavBar from "./NavBar";
import Footer from "./Footer";

const inter = Inter({ subsets: ["latin"] });

async function getFirstName(): Promise<string> {
  let response: Response = await fetch(BACKEND_DOMAIN + "/user/getFirstName");
  console.log("getFirstName");
  console.log(response);
  console.log(await response.text());
  return "hello";
}

export const metadata: Metadata = {
  title: "UsedPCParts.com",
  description: "Buy and sell used PC parts!",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  getFirstName();
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="min-h-screen flex flex-col space justify-between">
          <NavBar></NavBar>
          {children}
          <Footer></Footer>
        </div>
      </body>
    </html>
  );
}
