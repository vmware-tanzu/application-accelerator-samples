using Newtonsoft.Json;

namespace acme_order.Request
{
    public class TokenRequest
    {
        [JsonProperty("access_token")] public string AccessToken { get; set; }
    }
}