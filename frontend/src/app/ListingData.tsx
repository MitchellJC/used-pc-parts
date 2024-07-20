interface PcPart {
  name: string;
  description: string;
}

export default interface ListingData {
  id: number;
  name: string;
  description: string;
  price: number;
  pcPart: PcPart;
}
