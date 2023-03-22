package com.bank.profile.util;

import com.bank.profile.entity.Passport;
import com.bank.profile.entity.Profile;
import com.bank.profile.entity.Registration;
import com.bank.profile.service.serviceInterface.AccountDetailsIdService;
import com.bank.profile.service.serviceInterface.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitClass {
    private final ProfileService profileService;

    private final AccountDetailsIdService accountDetailsIdService;

    @Autowired
    public InitClass(ProfileService profileService, AccountDetailsIdService accountDetailsIdService) {
        this.profileService = profileService;
        this.accountDetailsIdService = accountDetailsIdService;
    }

    @PostConstruct
    @Transactional
    public void initiation() {
        Registration registration = new Registration();
        registration.setCountry("Российская Федерация");
        registration.setRegion("Московская область");
        registration.setCity("Москва");
        registration.setDistrict("Ленинский р-н");
        registration.setStreet("Пирогова");
        registration.setHouseNumber("42");
        registration.setFlatNumber("14");
        registration.setIndex(142536);


        Passport passport = new Passport();

        LocalDate birthDate = LocalDate.of(1994,10,13);
        LocalDate dateOfIssue = LocalDate.of(2011,11,05);
        LocalDate expirationDate = LocalDate.of(2032,10,13);

        passport.setSeries(2415);
        passport.setNumber(241536);
        passport.setLastName("Петров");
        passport.setFirstName("Игорь");
        passport.setMiddleName("Сергеевич");
        passport.setGender("МУЖ");
        passport.setBirthDate(birthDate);
        passport.setBirthPlace("Москва, Московская область, Российская федерация");
        passport.setIssuedBy("УМВД РФ по Ленинскому р-н г. Москвы");
        passport.setDateOfIssue(dateOfIssue);
        passport.setDivisionCode(365214);
        passport.setExpirationDate(expirationDate);
        passport.setRegistrationId(registration);



        Profile profile = new Profile();
        profile.setPhoneNumber(9059998877L);
        profile.setEmail("petrov@mail.ru");
        profile.setNameOnCard("PETROV I");
        profile.setInn(3265_1456_9547L);
        profile.setSnils(1245_2541_3605_01L);
        profile.setPassportId(passport);

        profileService.saveProfile(profile);


        Registration registration2 = new Registration();
        registration2.setCountry("Российская Федерация");
        registration2.setRegion("Ленинградская область");
        registration2.setCity("Санкт-Петербург");
        registration2.setDistrict("Ленинский р-н");
        registration2.setStreet("Меченова");
        registration2.setHouseNumber("4");
        registration2.setFlatNumber("15");
        registration2.setIndex(143236);


        Passport passport2 = new Passport();

        LocalDate birthDate2 = LocalDate.of(1995,11,13);
        LocalDate dateOfIssue2 = LocalDate.of(2010,11,05);
        LocalDate expirationDate2 = LocalDate.of(2031,10,13);

        passport2.setSeries(1425);
        passport2.setNumber(242567);
        passport2.setLastName("Смирнов");
        passport2.setFirstName("Владислав");
        passport2.setMiddleName("Иванович");
        passport2.setGender("МУЖ");
        passport2.setBirthDate(birthDate2);
        passport2.setBirthPlace("Москва, Московская область, Российская федерация");
        passport2.setIssuedBy("УМВД РФ по Ленинскому р-н г. Санкт-Петербург");
        passport2.setDateOfIssue(dateOfIssue2);
        passport2.setDivisionCode(425214);
        passport2.setExpirationDate(expirationDate2);
        passport2.setRegistrationId(registration2);



        Profile profile2 = new Profile();
        profile2.setPhoneNumber(9092223344L);
        profile2.setEmail("smirnov@mail.ru");
        profile2.setNameOnCard("SMIRNOV V");
        profile2.setInn(3264_1856_9547L);
        profile2.setSnils(9815_2541_3605_01L);
        profile2.setPassportId(passport2);

        profileService.saveProfile(profile2);
    }
}
