using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using AccountsApi.Data;

namespace AccountsApi.Controllers
{
    public class BankAccountsController : Controller
    {
        private readonly AccountsContext _context;

        public BankAccountsController(AccountsContext context)
        {
            _context = context;
        }

        // GET: BankAccounts
        public async Task<IActionResult> Index()
        {
            return View(await _context.SavingsAccounts.ToListAsync());
        }

        // GET: BankAccounts/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var bankAccount = await _context.SavingsAccounts
                .FirstOrDefaultAsync(m => m.Id == id);
            if (bankAccount == null)
            {
                return NotFound();
            }

            return View(bankAccount);
        }

        private bool BankAccountExists(int id)
        {
            return _context.SavingsAccounts.Any(e => e.Id == id);
        }
    }
}
