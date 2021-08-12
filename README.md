# SELENIUM 

**especificando onde está o driver no teu pc:** </br>
  :arrow_right: System.setProperty("webdriver.driverutilizado.driver", "caminho do driver");
    
**Instanciando o driver:** </br>
  :arrow_right: WebDriver  driver= new Webdriver()
  
**Informando a página que será acessada:** </br>
 :arrow_right: driver.get("http://www.caminhodapagina.com)
  
**Definir tamanho e posição do Browse:**r </br>
:arrow_right: driver.manage( ).window( ).setposition(new point(x, y)); </br>
:arrow_right: driver.manage( ).window( ).setposition(new dimension(L, H)); </br>
:arrow_right: driver.manage( ).window( ).maxmize( );

**Buscando elementos:** </br>
:arrow_right: driver.findElement(By.id()); </br>
:arrow_right: driver.findElement(By.xpath()); </br>
:arrow_right: driver.findElement(By.linkText()); </br>
:arrow_right: driver.findElement(By.tagName()); </br>
:arrow_right: driver.findElement(By.className()); </br>
:arrow_right: driver.findElement(By.partialLinkText()); </br>
:arrow_right: driver.findElement(By.name()); </br>
:arrow_right: driver.findElement(By.cssSelector()); </br>

**Escrever em campos de texto:** </br>
:arrow_right: .sendKeys("Texto") </br>
:arrow_right: .sendKeys("Texto /n/n em mais de /n/n uma linha") 

**Pegar o valor que está escrito dentro de um campo de texto:** </br>
:arrow_right: .getAttribute("value")






