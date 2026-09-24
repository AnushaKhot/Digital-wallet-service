package com.wallet;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "ledger_entries")
class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    private String transactionType; // CREDIT or DEBIT
    private Double amount;
    private LocalDateTime timestamp;

    public LedgerEntry() {}

    public LedgerEntry(String userEmail, String transactionType, Double amount) {
        this.userEmail = userEmail;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getTransactionType() { return transactionType; }
    public Double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {
    List<LedgerEntry> findByUserEmail(String userEmail);
}

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    private final LedgerRepository repository;

    public WalletController(LedgerRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/transaction")
    public ResponseEntity<LedgerEntry> recordTransaction(@RequestBody Map<String, Object> payload) {
        String email = payload.get("userEmail").toString();
        String type = payload.get("type").toString();
        Double amount = Double.parseDouble(payload.get("amount").toString());

        LedgerEntry entry = new LedgerEntry(email, type, amount);
        return ResponseEntity.ok(repository.save(entry));
    }

    @GetMapping("/ledger/{email}")
    public ResponseEntity<List<LedgerEntry>> getLedgerHistory(@PathVariable String email) {
        return ResponseEntity.ok(repository.findByUserEmail(email));
    }
}