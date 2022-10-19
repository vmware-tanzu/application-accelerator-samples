using System;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Authentication;
using System.Text;
using acme_order.Configuration;
using acme_order.Request;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.Net.Http.Headers;
using Newtonsoft.Json;

namespace acme_order.Auth
{
    public sealed class AuthorizeResource : IActionFilter
    {
        private static IAcmeServiceSettings _acmeServiceSettings;

        public AuthorizeResource(IAcmeServiceSettings acmeServiceSettings)
        {
            _acmeServiceSettings = acmeServiceSettings;
        }

        public void OnActionExecuted(ActionExecutedContext context)
        {
            
        }

        public void OnActionExecuting(ActionExecutingContext context)
        {
            var headers = context.HttpContext.Request.Headers;

            if (!headers.Keys.Any(x => x.Equals(HeaderNames.Authorization))) throw new AuthenticationException();
            var accessToken = headers[HeaderNames.Authorization];
            accessToken = accessToken.ToString().Replace("Bearer ", "");
            
            if (string.IsNullOrEmpty(accessToken)) throw new AuthenticationException();
            VerifyToken(accessToken);
        }


        private static async void VerifyToken(string accessToken)
        {
            var tokenRequest = new TokenRequest
            {
                AccessToken = accessToken
            };

            var json = JsonConvert.SerializeObject(tokenRequest);
            var data = new StringContent(json, Encoding.UTF8, "application/json");
            var url = $"{_acmeServiceSettings.AuthUrl}/verify-token";

            using var client = new HttpClient();

            var response = await client.PostAsync(url, data);

            if (!response.IsSuccessStatusCode)
            {
                throw new AuthenticationException();
            }
        }
    }
}