def coin_change(coins, amount):
    # Buat array untuk menyimpan jumlah minimum koin untuk setiap nilai dari 0 hingga amount
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0  # Tidak perlu koin untuk jumlah 0

    # Iterasi untuk setiap koin
    for coin in coins:
        for x in range(coin, amount + 1):
            dp[x] = min(dp[x], dp[x - coin] + 1)

    # Jika dp[amount] masih inf, berarti tidak ada solusi
    return dp[amount] if dp[amount] != float('inf') else -1

# Daftar koin yang tersedia
coins = [2, 3, 5, 10, 15]

# Input jumlah yang ingin dicapai
amount = int(input("Masukkan jumlah yang ingin dicapai: "))

# Menghitung jumlah minimum koin
min_coins = coin_change(coins, amount)

if min_coins != -1:
    print(f"Jumlah minimum koin yang dibutuhkan: {min_coins}")
else:
    print("Tidak ada kombinasi koin yang dapat mencapai jumlah tersebut.")