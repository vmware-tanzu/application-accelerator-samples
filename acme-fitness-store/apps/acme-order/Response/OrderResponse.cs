using System.Collections.Generic;
using acme_order.Models;

namespace acme_order.Response
{
    public class OrderResponse
    {
        public string Userid { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public Address Address { get; set; }
        public string Email { get; set; }
        public string Delivery { get; set; }
        public Card Card { get; set; }
        public ICollection<Cart> Cart { get; set; }
        public string Total { get; set; }
    }
}