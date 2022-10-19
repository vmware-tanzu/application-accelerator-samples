using acme_order.Models;
using Newtonsoft.Json;

namespace acme_order.Response
{
    public class OrderCreateResponse
    {
        [JsonProperty("userid")] public string UserId { get; set; }
        [JsonProperty("order_id")] public string OrderId { get; set; }
        public Payment Payment { get; set; }
    }
}