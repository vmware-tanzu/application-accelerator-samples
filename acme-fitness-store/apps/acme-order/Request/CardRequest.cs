using Newtonsoft.Json;

namespace acme_order.Request
{
    public class CardRequest
    {
        [JsonProperty("number")] public string Number { get; set; }
        [JsonProperty("expMonth")] public string ExpMonth { get; set; }
        [JsonProperty("expYear")] public string ExpYear { get; set; }
        [JsonProperty("ccv")] public string Ccv { get; set; }
        
        [JsonProperty("type")] public string Type { get; set; }
    }
}