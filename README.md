Kütüphane Yönetim Sistemi

 SQLite kullanarak kalıcı veri yönetimi sağlar ve bir kütüphanedeki temel işlemlerin (kitap, öğrenci, ödünç alma) dijital olarak yönetilmBu proje, Nesneye Yönelik Programlama (OOP) dersi kapsamında geliştirilmiş Java tabanlı bir konsol uygulamasıdır. Sistem; JDBC veesini amaçlar.

📌 Proje Tanımı

SmartLibrary, üniversite kütüphanelerinde sık kullanılan süreçleri modelleyen küçük ölçekli bir yönetim sistemidir. Uygulama; kitapların kaydedilmesi, öğrencilerin sisteme eklenip silinmesi ve kitap ödünç alma–iade işlemlerinin tarih bazlı olarak takip edilmesini sağlar.

🛠 Kullanılan Teknolojiler

Programlama Dili: Java (JDK 17, JDK 8 ile uyumlu)

IDE: Visual Studio Code

Veritabanı: SQLite

Kütüphane: SQLite JDBC Driver (sqlite-jdbc-xxxx.jar)

Mimari: Katmanlı yapı (Entities – Repositories – Database Access)

🎯 Özellikler

Proje, ders kapsamında talep edilen gereksinimleri tam olarak karşılar:

OOP Yapısı: Book, Student, Loan sınıfları ile modelleme yapılmıştır.

Encapsulation: Tüm değişkenler private, erişimler Getter/Setter üzerinden yapılır.

Repository Pattern: Veri işlemleri ayrı repository sınıflarında ele alınmıştır.

JDBC Bağlantısı: Veriler doğrudan smartlibrary.db dosyasında saklanır.

Koleksiyonlar: Listelemelerde ArrayList yapısı kullanılır.

Otomatik Tablo Oluşturma: Tablolar yoksa sistem tarafından oluşturulur.

İş Kuralı Kontrolü: Ödünçte olan bir kitabın başka bir öğrenciye verilmesi engellenir.

📁 Proje Yapısı
SmartLibrary/
│
├── smartlibrary.db          # Çalıştırıldığında oluşturulan veritabanı
├── sqlite-jdbc-3.46.0.0.jar # JDBC sürücüsü
├── README.md                # Dokümantasyon
│
├── Main.java                # Giriş noktası ve menü yönetimi
├── Database.java            # SQLite bağlantısı ve tablo işlemleri
│
├── Book.java                # Kitap varlık sınıfı
├── Student.java             # Öğrenci varlık sınıfı
├── Loan.java                # Ödünç alma varlık sınıfı
│
├── BookRepository.java      # Kitap CRUD işlemleri
├── StudentRepository.java   # Öğrenci CRUD işlemleri
└── LoanRepository.java      # Ödünç alma–iade işlemleri

⚙️ Kurulum ve Çalıştırma (VS Code)
1. Gereksinimler

Bilgisayarınızda JDK kurulu olmalıdır.

VS Code ve Extension Pack for Java eklentisi yüklü olmalıdır.

2. Projeyi Açma

Proje klasörünü File > Open Folder ile VS Code üzerinden açın.

3. JDBC Sürücüsünü Eklemek (Önemli)

sqlite-jdbc-xxxx.jar dosyasını proje klasörüne yerleştirin.

Sol panelden JAVA PROJECTS bölümünü açın.

Referenced Libraries altındaki + düğmesine tıklayın.

.jar dosyasını seçerek projeye ekleyin.

4. Projeyi Çalıştırma

Main.java dosyasını açın.

Sağ üstteki ▷ Run butonuna basın veya F5 tuşuna basın.

Terminalde menü otomatik olarak görüntülenecektir.

🗄 Veritabanı Şeması
Books Tablosu
id	title	author	year
Auto Inc	Kitap Adı	Yazar	Basım Yılı
Students Tablosu
id	name	department
Auto Inc	Ad Soyad	Bölüm
Loans Tablosu
id	bookId	studentId	dateBorrowed	dateReturned
Auto Inc	Kitap ID	Öğrenci ID	Alınan Tarih	Teslim Tarihi (null olabilir)
📘 Kullanım Senaryoları

Sistemde kullanıcıya bir menü sunulur ve şu işlemler yapılabilir:

Kitap Ekle – Yeni kitap kaydı oluşturur.

Kitapları Listele – Tüm kitapları görüntüler.

Öğrenci Ekle – Yeni öğrenci ekler.

Öğrencileri Listele – Kayıtlı öğrencileri gösterir.

Kitap Ödünç Ver – Bir kitabı bir öğrenciye teslim eder.

Kitap ödünçteyse sistem uyarı verir.

Ödünç Listesini Görüntüle – Tüm ödünç alma işlemlerini listeler.

Kitap Geri Teslim Al – Kitap için iade tarihi girilir.

Kitap Sil – Seçilen kitap sistemden tamamen silinir.

Öğrenci Sil – Belirtilen öğrenci kaydı kaldırılır.
meriç aydemir 20230108049



