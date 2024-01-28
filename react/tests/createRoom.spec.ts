import { test, expect } from '@playwright/test';

test('should be able to create room.', async ({ page }) => {
  await page.goto('http://localhost:8080/');

  await page.locator('button > img[alt="へやをつくる"]').click();

  await expect(page).toHaveURL(/room/);
});
