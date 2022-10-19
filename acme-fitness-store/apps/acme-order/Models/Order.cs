using System;
using System.Collections.Generic;

namespace acme_order.Models
{
    public class Order
    {
        public Guid Id { get; set; }
        public DateTime? Date { get; set; }
        public string Paid { get; set; }
        public string UserId { get; set; }
        public string Firstname { get; set; }
        public string Lastname { get; set; }
        public string Email { get; set; }
        public string Delivery { get; set; }
        public string Total { get; set; }
        public Address Address { get; set; }
        public Card Card { get; set; }
        public ICollection<Cart> Cart { get; set; }
    }
}