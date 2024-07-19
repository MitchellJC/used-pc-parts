function DisplayCase({ listings }: { listings: string[] }): JSX.Element {
  const listingItems: JSX.Element[] = listings.map((listing) => (
    <li key={listing}>{listing}</li>
  ));
  return <ul>{listingItems}</ul>;
}

export default DisplayCase;
