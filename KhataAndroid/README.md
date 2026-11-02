# Khata – Shop Ledger (Android Studio project)

Dukaan ka udaar book + inventory + mahine ki profit report.

## Kaise chalayein
1. Android Studio (Ladybug ya naya) mein **File → Open** karke ye folder kholein.
2. Gradle sync hone dein (internet chahiye, pehli baar).
3. Phone ya emulator chun kar **Run ▶**.

## Structure
- `app/src/main/assets/index.html` — poori app ka UI aur logic (HTML/JS)
- `app/.../Db.kt` — Room (SQLite) database: `snapshot` table (poora data) aur `backup` table (roz ka backup, aakhri 14)
- `app/.../Bridge.kt` — web page aur database ke beech pul (`AndroidDB.get()` / `AndroidDB.set()`)
- `app/.../MainActivity.kt` — WebView, jahan UI chalta hai

Koi server nahi hai: sab data phone ke andar database mein rehta hai.

## Play Store se pehle
- `applicationId` (com.example.khata) apna rakhein.
- `targetSdk` Play ki current requirement ke mutabiq rakhein.
- Privacy policy, icon aur screenshots tayyar rakhein.
- Release ke liye **Build → Generate Signed App Bundle** use karein.
