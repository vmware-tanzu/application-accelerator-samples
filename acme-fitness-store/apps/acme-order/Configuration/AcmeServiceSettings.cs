using System;

namespace acme_order.Configuration
{
    public class AcmeServiceSettings : IAcmeServiceSettings
    {
        public string AuthUrl { get; set; }
    }
    
    public interface IAcmeServiceSettings
    {
        public string AuthUrl { get; set; }
    }
}