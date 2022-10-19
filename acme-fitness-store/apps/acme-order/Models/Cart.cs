namespace acme_order.Models
{
    public class Cart
    {
        public string Id { get; set; }
        public string Description { get; set; }
        public int Quantity { get; set; }
        public string Price { get; set; }
    }
}