package ru.netology;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.data.SqlHelper;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TourPurchaseTest {

    DashboardPage dashboardPage = new DashboardPage();

    @BeforeEach
    void setup() {
        open(System.getProperty("sut.url"));
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.cleanDataBase();
    }

    @Test
    @DisplayName("Оплата по одобренной кредитной карте")
    public void shouldPayByAppDCCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        creditCardPage.cardInfo(approvedCardInformation);
        creditCardPage.okNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по отклоненной кредитной карте")
    public void shouldPayNotByDecDCCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        creditCardPage.cardInfo(declinedCardInformation);
        creditCardPage.nokNotification();
        val paymentStatus = SqlHelper.getCreditEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по кредитной карте с невалидным номером")
    public void shouldNotPayByInvNumCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        creditCardPage.cardInfo(invalidCardInformation);
        creditCardPage.messInvalidCardNumber();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с неполным номером")
    public void shouldErrorNotFullNumCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        creditCardPage.cardInfo(notFullCardInformation);
        creditCardPage.messErrorNum();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с невалидным месяцем")
    public void shouldErrorInvalidMonthCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        creditCardPage.cardInfo(invalidMonthCardInformation);
        creditCardPage.messInvalidMonth();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием истекшего месяца")
    public void shouldErrorExpiredMonthCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        creditCardPage.cardInfo(expiredMonthCardInformation);
        creditCardPage.messExpiredMonth();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием истекшего года")
    public void shouldErrorExpiredYearCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        creditCardPage.cardInfo(expiredYearCardInformation);
        creditCardPage.messExpiredYearField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием невалидных значений в поле Владелец")
    public void shouldErrorInvalidOwnerCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();;
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        creditCardPage.cardInfo(invalidOwner);
        creditCardPage.messInvalidOwner();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с указанием невалидных значений в поле Cvc")
    public void shouldErrorCvcCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        creditCardPage.cardInfo(invalidCvc);
        creditCardPage.messInvalidCvc();
    }

    @Test
    @DisplayName("Отравка пустой формы")
    public void shouldNotSendEmptyFormCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        creditCardPage.cardInfo(emptyForm);
        creditCardPage.messEmptyCardNumberField();
        creditCardPage.messEmptyMonthField();
        creditCardPage.messEmptyYearField();
        creditCardPage.messEmptyOwnerField();
        creditCardPage.messEmptyCvcField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Номер карты")
    public void shouldErrorEmptyCardNumCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        creditCardPage.cardInfo(emptyCardNum);
        creditCardPage.messEmptyCardNumberField();
    }

    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Месяц")
    public void shouldErrorEmptyMonthCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        creditCardPage.cardInfo(emptyMonth);
        creditCardPage.messEmptyMonthField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Год")
    public void shouldErrorEmptyYearCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyYear = DataHelper.getEmptyYear();
        creditCardPage.cardInfo(emptyYear);
        creditCardPage.messEmptyYearField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Владелец")
    public void shouldErrorEmptyOwnerCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        creditCardPage.cardInfo(emptyOwner);
        creditCardPage.messEmptyOwnerField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с пустым полем Cvc")
    public void shouldErrorEmptyCvcCreditCrad() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        creditCardPage.cardInfo(emptyCvc);
        creditCardPage.messEmptyCvcField();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 000 в поле Cvc")
    public void shouldErrorZeroCvcCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroCvc = DataHelper.getZeroCvc();
        creditCardPage.cardInfo(zeroCvc);
        creditCardPage.messInvalidCvc();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Номер карты")
    public void shouldErrorZeroCardNumCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        creditCardPage.cardInfo(zeroCardNum);
        creditCardPage.messZeroNum();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Месяц")
    public void shouldErrorZeroMonthCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        creditCardPage.cardInfo(zeroMonth);
        creditCardPage.messInvalidMonth();
    }
    @Test
    @DisplayName("Оплата по кредитной карте с вводом 0 в поле Год")
    public void shouldErrorZeroYearCreditCard() {
        val creditCardPage = dashboardPage.payByCreditCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        creditCardPage.cardInfo(zeroYear);
        creditCardPage.messInvalidYear();
    }
    @Test
    @DisplayName("Оплата по одобренной дебитовой карте")
    public void shouldPayByAppDCDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInfo();
        debitCardPage.cardInfo(approvedCardInformation);
        debitCardPage.okNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по отклоненной дебитовой карте")
    public void shouldPayNotByDecDCDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInfo();
        debitCardPage.cardInfo(declinedCardInformation);
        debitCardPage.nokNotification();
        val paymentStatus = SqlHelper.getPaymentEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным номером")
    public void shouldNotPayByInvNumDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInfo();
        debitCardPage.cardInfo(invalidCardInformation);
        debitCardPage.messInvalidCardNumber();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с неполным номером")
    public void shouldErrorNotFullNumDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val notFullCardInformation = DataHelper.getNotFullCardInfo();
        debitCardPage.cardInfo(notFullCardInformation);
        debitCardPage.messErrorNum();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с невалидным месяцем")
    public void shouldErrorInvalidMonthDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidMonthCardInformation = DataHelper.getInvalidMonthCardInfo();
        debitCardPage.cardInfo(invalidMonthCardInformation);
        debitCardPage.messInvalidMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего месяца")
    public void shouldErrorExpiredMonthDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInfo();
        debitCardPage.cardInfo(expiredMonthCardInformation);
        debitCardPage.messExpiredMonth();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием истекшего года")
    public void shouldErrorExpiredYearDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInfo();
        debitCardPage.cardInfo(expiredYearCardInformation);
        debitCardPage.messExpiredYearField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Владелец")
    public void shouldErrorInvalidOwnerDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidOwner = DataHelper.getInvalidOwnerCard();
        debitCardPage.cardInfo(invalidOwner);
        debitCardPage.messInvalidOwner();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с указанием невалидных значений в поле Cvc")
    public void shouldErrorCvcDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val invalidCvc = DataHelper.getInvalidCvc();
        debitCardPage.cardInfo(invalidCvc);
        debitCardPage.messInvalidCvc();
    }

    @Test
    @DisplayName("Отравка пустой формы")
    public void shouldNotSendEmptyFormDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyForm = DataHelper.getEmptyCardInfo();
        debitCardPage.cardInfo(emptyForm);
        debitCardPage.messEmptyCardNumberField();
        debitCardPage.messEmptyMonthField();
        debitCardPage.messEmptyYearField();
        debitCardPage.messEmptyOwnerField();
        debitCardPage.messEmptyCvcField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Номер карты")
    public void shouldErrorEmptyCardNumDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyCardNum = DataHelper.getEmptyCardNumber();
        debitCardPage.cardInfo(emptyCardNum);
        debitCardPage.messEmptyCardNumberField();
    }

    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Месяц")
    public void shouldErrorEmptyMonthDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyMonth = DataHelper.getEmptyMonth();
        debitCardPage.cardInfo(emptyMonth);
        debitCardPage.messEmptyMonthField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Год")
    public void shouldErrorEmptyYearDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyYear = DataHelper.getEmptyYear();
        debitCardPage.cardInfo(emptyYear);
        debitCardPage.messEmptyYearField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Владелец")
    public void shouldErrorEmptyOwnerDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyOwner = DataHelper.getEmptyOwner();
        debitCardPage.cardInfo(emptyOwner);
        debitCardPage.messEmptyOwnerField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с пустым полем Cvc")
    public void shouldErrorEmptyCvcDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val emptyCvc = DataHelper.getEmptyCvc();
        debitCardPage.cardInfo(emptyCvc);
        debitCardPage.messEmptyCvcField();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 000 в поле Cvc")
    public void shouldErrorZeroCvcDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroCvc = DataHelper.getZeroCvc();
        debitCardPage.cardInfo(zeroCvc);
        debitCardPage.messInvalidCvc();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Номер карты")
    public void shouldErrorZeroCardNumDebit() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroCardNum = DataHelper.getCardZeroNumber();
        debitCardPage.cardInfo(zeroCardNum);
        debitCardPage.messZeroNum();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Месяц")
    public void shouldErrorZeroMonthDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroMonth = DataHelper.getZeroMonthCardInfo();
        debitCardPage.cardInfo(zeroMonth);
        debitCardPage.messInvalidMonth();
    }
    @Test
    @DisplayName("Оплата по дебитовой карте с вводом 0 в поле Год")
    public void shouldErrorZeroYearDebitCard() {
        val debitCardPage = dashboardPage.payByDebitCard();
        val zeroYear = DataHelper.getZeroYearCardInformation();
        debitCardPage.cardInfo(zeroYear);
        debitCardPage.messInvalidYear();
    }
}