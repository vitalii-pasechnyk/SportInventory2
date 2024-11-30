package org.laba2.sportinventory2.service;

import org.laba2.sportinventory2.dto.BonusCardDTO;
import org.laba2.sportinventory2.entity.BonusCard;
import org.laba2.sportinventory2.repository.BonusCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BonusCardService {

    private final BonusCardRepository bonusCardRepository;

    public BonusCardService(BonusCardRepository bonusCardRepository) {
        this.bonusCardRepository = bonusCardRepository;
    }

    public BonusCardDTO createBonusCard(BonusCardDTO bonusCardDTO) {
        BonusCard bonusCard = dtoToEntity(bonusCardDTO);
        bonusCard = bonusCardRepository.save(bonusCard);
        return entityToDTO(bonusCard);
    }

    public BonusCardDTO getBonusCardById(Long id) {
        BonusCard bonusCard = bonusCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BonusCard not found"));
        return entityToDTO(bonusCard);
    }

    public BonusCardDTO updateBonusCard(Long id, BonusCardDTO updatedCardDTO) {
        BonusCard bonusCard = bonusCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BonusCard not found"));

        bonusCard.setCardNumber(updatedCardDTO.getCardNumber());
        bonusCard.setBalance(updatedCardDTO.getBalance());
        bonusCard = bonusCardRepository.save(bonusCard);

        return entityToDTO(bonusCard);
    }

    public void deleteBonusCard(Long id) {
        if (!bonusCardRepository.existsById(id)) {
            throw new RuntimeException("BonusCard not found");
        }
        bonusCardRepository.deleteById(id);
    }
    public List<BonusCardDTO> getAllBonusCards() {
        List<BonusCard> bonusCards = bonusCardRepository.findAll();  // Отримуємо всі бонусні карти з бази
        return bonusCards.stream()
                .map(this::convertToDTO)  // Перетворюємо на DTO
                .collect(Collectors.toList());
    }
    private BonusCardDTO convertToDTO(BonusCard bonusCard) {
        return new BonusCardDTO(bonusCard.getId(), bonusCard.getCardNumber(), bonusCard.getBalance());
    }

    public void addBonus(Long cardId, double rentalAmount) {
        BonusCard bonusCard = bonusCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Bonus card not found"));
        bonusCard.addBonus(rentalAmount); bonusCardRepository.save(bonusCard);
    }


    private BonusCardDTO entityToDTO(BonusCard bonusCard) {
        BonusCardDTO bonusCardDTO = new BonusCardDTO();
        bonusCardDTO.setId(bonusCard.getId());
        bonusCardDTO.setCardNumber(bonusCard.getCardNumber());
        bonusCardDTO.setBalance(bonusCard.getBalance());
        return bonusCardDTO;
    }


    private BonusCard dtoToEntity(BonusCardDTO bonusCardDTO) {
        BonusCard bonusCard = new BonusCard();
        bonusCard.setCardNumber(bonusCardDTO.getCardNumber());
        bonusCard.setBalance(bonusCardDTO.getBalance());
        return bonusCard;
    }
}
