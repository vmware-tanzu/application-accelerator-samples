using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using RestService.Models;

namespace RestService.Controllers;

[Route("api/customer-profiles")]
[ApiController]
public class CustomerProfilesController : ControllerBase
{
    private readonly CustomerProfilesContext _context;

    private readonly ILogger<CustomerProfilesController> _logger;

    public CustomerProfilesController(CustomerProfilesContext context, ILogger<CustomerProfilesController> logger)
    {
        _context = context;
        _logger = logger;
    }

    [HttpGet]
    public async Task<ActionResult<IEnumerable<CustomerProfile>>> GetCustomerProfiles()
    {
        return await _context.CustomerProfiles.ToListAsync();
    }

    [HttpGet("{id}")]
    public async Task<ActionResult<CustomerProfile>> GetCustomerProfile(string id)
    {
        var customerProfile = await _context.CustomerProfiles.FindAsync(id);

        if (customerProfile == null)
        {
            return NotFound();
        }

        return customerProfile;
    }

    [HttpPut("{id}")]
    public async Task<IActionResult> PutCustomerProfile(string id, CustomerProfile customerProfile)
    {
        if (id != customerProfile.Id)
        {
            return BadRequest();
        }

        _context.Entry(customerProfile).State = EntityState.Modified;

        try
        {
            await _context.SaveChangesAsync();
        }
        catch (DbUpdateConcurrencyException)
        {
            if (!CustomerProfileExists(id))
            {
                return NotFound();
            }
            else
            {
                throw;
            }
        }

        return NoContent();
    }


    [HttpPost]
    public async Task<ActionResult<CustomerProfile>> PostCustomerProfile(CustomerProfile customerProfile)
    {
        _context.CustomerProfiles.Add(customerProfile);
        await _context.SaveChangesAsync();

        return CreatedAtAction("GetCustomerProfile", new { id = customerProfile.Id }, customerProfile);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> DeleteCustomerProfile(string id)
    {
        var customerProfile = await _context.CustomerProfiles.FindAsync(id);
        if (customerProfile == null)
        {
            return NotFound();
        }

        _context.CustomerProfiles.Remove(customerProfile);
        await _context.SaveChangesAsync();

        return NoContent();
    }

    private bool CustomerProfileExists(string id)
    {
        return _context.CustomerProfiles.Any(e => e.Id == id);
    }
}
