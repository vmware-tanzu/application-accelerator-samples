using System.Text.Json.Serialization;
using acme_order.Models;
using Newtonsoft.Json;

namespace acme_order.Request
{
    public class PaymentRequest
    {
        [JsonProperty("card")] public CardRequest Card { get; set; }
        [JsonProperty("total")] public string Total { get; set; }
    }
}