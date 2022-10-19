namespace acme_order.Models
{
    public class Card
    {
        public string Type { get; set; }
        public string Number { get; set; }
        public string ExpMonth { get; set; }
        public string ExpYear { get; set; }
        public string Ccv { get; set; }
    }
}