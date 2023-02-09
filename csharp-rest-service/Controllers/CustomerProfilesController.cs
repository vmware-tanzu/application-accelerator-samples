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

    // GET: api/customer-profiles
    [HttpGet]
    public async Task<ActionResult<IEnumerable<CustomerProfile>>> GetCustomerProfiles()
    {
        return await _context.CustomerProfiles.ToListAsync();
    }

    // GET: api/customer-profiles/
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

    // PUT: api/customer-profiles/5
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
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


    // POST: api/customer-profiles
    // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
    [HttpPost]
    public async Task<ActionResult<CustomerProfile>> PostCustomerProfile(CustomerProfile customerProfile)
    {
        _context.CustomerProfiles.Add(customerProfile);
        await _context.SaveChangesAsync();

        return CreatedAtAction("GetCustomerProfile", new { id = customerProfile.Id }, customerProfile);
    }

    // DELETE: api/customer-profiles/0bc6ab17-7ce2-4e26-ac78-c9f3b08dd8b2
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
