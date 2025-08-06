@RestController
public class HomeController {

    @PostMapping("/contact")
    public ResponseEntity<String> submitContact(@Valid @RequestBody Contact contact) {
        // Just a placeholder response
        return ResponseEntity.ok("Message received (email not sent)");
    }
}
