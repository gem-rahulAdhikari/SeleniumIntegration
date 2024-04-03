Report_1712144179_0"https://www.google.com/");
        WebElement searchInput = driver.findElement(By.xpath("//textarea"));
        searchInput.sendKeys("selenium");
        searchInput.sendKeys(Keys.RETURN);
        WebElement title = driver.findElement(By.xpath("(//h3[text()='Selenium'])[1]"));
        String fetchedTitle = title.getText();
       
}



























}