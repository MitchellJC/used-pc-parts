interface ListingData {
  id: number;
  name: string;
  description: string;
  price: number;
}

function ListingDisplay({
  name,
  description,
  price,
}: {
  name: string;
  description: string;
  price: number;
}): JSX.Element {
  return (
    <div>
      <image></image>
      <span>{name}</span>
      <p>{description}</p>
      <span>{price}</span>
    </div>
  );
}

function DisplayCase({ listings }: { listings: ListingData[] }): JSX.Element {
  const listingItems: JSX.Element[] = listings.map((listing) => (
    <li key={listing.id}>
      <ListingDisplay
        name={listing.name}
        description={listing.description}
        price={listing.price}
      ></ListingDisplay>
    </li>
  ));
  return (
    <div>
      <h2 className="text-xl">New</h2>
      <ul className="flex gap-3">{listingItems}</ul>
    </div>
  );
}

export default DisplayCase;
