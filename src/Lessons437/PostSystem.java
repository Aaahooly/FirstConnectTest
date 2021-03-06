package Lessons437;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PostSystem {
    Logger loging;
    public static void main(String[] args) {
        int mkmk = 55;
        int dsd = 44;
        System.out.println("ะจะตะป {0}" + mkmk);
    }

    public static class IllegalPackageException extends RuntimeException {}

    public static class StolenPackageException extends RuntimeException {}

    public static class Thief implements MailService {
        private int minPrice = 0;
        private int stolenPrice = 0;

        public int getMinPrice() {
            return minPrice;
        }

        public Thief(int minPrice) {
            this.minPrice = minPrice;
        }

        public int getStolenValue() {
            return stolenPrice;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                Package pack = mailPackage.getContent();
                if (pack.getPrice() >= this.getMinPrice()) {
                    this.stolenPrice += pack.getPrice();
                    mail = new MailPackage(mailPackage.getFrom(), mailPackage.getTo(), new Package("stones instead of " + pack.getContent(), 0));
                }
            }
            return mail;
        }
    }

    public static class Spy implements MailService {
        private Logger LOGGER;

        public Spy(Logger logger) {
            LOGGER = logger;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailMessage) {
                MailMessage mailMessage = (MailMessage) mail;
                if (mailMessage.getFrom().contains("Austin Powers") || mailMessage.getTo().contains("Austin Powers")) {
                    LOGGER.warning("Detected target mail correspondence: from " + mailMessage.getFrom() + " to " + mailMessage.getTo() + " \"" + mailMessage.getMessage() + "\"");
                } else {
                    LOGGER.info("Usual correspondence: from " + mailMessage.getFrom() + " to " + mailMessage.getTo());
                }
            }
            return mail;
        }

    }

    public static class Inspector implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            if (mail instanceof MailPackage) {
                MailPackage mailPackage = (MailPackage) mail;
                Package pack = mailPackage.getContent();
                if (pack.getContent().contains("weapons")) {
                    throw new IllegalPackageException();
                } else if (pack.getContent().contains("banned substance")) {
                    throw new IllegalPackageException();
                } else if (pack.getContent().contains("stones")) {
                    throw new StolenPackageException();
                }
            }
            return mail;
        }
    }

    public static class UntrustworthyMailWorker implements MailService {
        RealMailService realMailService = new RealMailService();
        MailService[] mailService;

        public UntrustworthyMailWorker(MailService[] mailServices) {
            this.mailService = mailServices;
        }

        public MailService getRealMailService() {
            return realMailService;
        }

        @Override
        public Sendable processMail(Sendable mail) {
            Sendable proc = mail;
            for (MailService mailService: mailService) {
                proc = mailService.processMail(proc);
            }
            return getRealMailService().processMail(proc);
        }
    }

    /*
    ะะฝัะตััะตะนั: ัััะฝะพััั, ะบะพัะพััั ะผะพะถะฝะพ ะพัะฟัะฐะฒะธัั ะฟะพ ะฟะพััะต.
    ะฃ ัะฐะบะพะน ัััะฝะพััะธ ะผะพะถะฝะพ ะฟะพะปััะธัั ะพั ะบะพะณะพ ะธ ะบะพะผั ะฝะฐะฟัะฐะฒะปัะตััั ะฟะธััะผะพ.
    */
    public static interface Sendable {
        String getFrom();

        String getTo();
    }

    /*
    ะะฑัััะฐะบัะฝัะน ะบะปะฐัั,ะบะพัะพััะน ะฟะพะทะฒะพะปัะตั ะฐะฑัััะฐะณะธัะพะฒะฐัั ะปะพะณะธะบั ััะฐะฝะตะฝะธั
    ะธััะพัะฝะธะบะฐ ะธ ะฟะพะปััะฐัะตะปั ะฟะธััะผะฐ ะฒ ัะพะพัะฒะตัััะฒัััะธั ะฟะพะปัั ะบะปะฐััะฐ.
    */
    public static abstract class AbstractSendable implements Sendable {

        protected final String from;
        protected final String to;

        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() {
            return from;
        }

        @Override
        public String getTo() {
            return to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }

    }

    /*
    ะะธััะผะพ, ั ะบะพัะพัะพะณะพ ะตััั ัะตะบัั, ะบะพัะพััะน ะผะพะถะฝะพ ะฟะพะปััะธัั ั ะฟะพะผะพััั ะผะตัะพะดะฐ `getMessage`
    */
    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    /*
    ะะพััะปะบะฐ, ัะพะดะตัะถะธะผะพะต ะบะพัะพัะพะน ะผะพะถะฝะพ ะฟะพะปััะธัั ั ะฟะพะผะพััั ะผะตัะพะดะฐ `getContent`
    */
    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    /*
    ะะปะฐัั, ะบะพัะพััะน ะทะฐะดะฐะตั ะฟะพััะปะบั. ะฃ ะฟะพััะปะบะธ ะตััั ัะตะบััะพะฒะพะต ะพะฟะธัะฐะฝะธะต ัะพะดะตัะถะธะผะพะณะพ ะธ ัะตะปะพัะธัะปะตะฝะฝะฐั ัะตะฝะฝะพััั.
    */
    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    /*
    ะะฝัะตััะตะนั, ะบะพัะพััะน ะทะฐะดะฐะตั ะบะปะฐัั, ะบะพัะพััะน ะผะพะถะตั ะบะฐะบะธะผ-ะปะธะฑะพ ะพะฑัะฐะทะพะผ ะพะฑัะฐะฑะพัะฐัั ะฟะพััะพะฒัะน ะพะฑัะตะบั.
    */
    public static interface MailService {
        Sendable processMail(Sendable mail);
    }

    /*
    ะะปะฐัั, ะฒ ะบะพัะพัะพะผ ัะบัััะฐ ะปะพะณะธะบะฐ ะฝะฐััะพััะตะน ะฟะพััั
    */
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // ะะดะตัั ะพะฟะธัะฐะฝ ะบะพะด ะฝะฐััะพััะตะน ัะธััะตะผั ะพัะฟัะฐะฒะบะธ ะฟะพััั.
            return mail;
        }
    }


}