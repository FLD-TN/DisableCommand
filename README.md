# DisableCommand Plugin

Plugin Minecraft để disable các command và ngăn người chơi không có quyền sử dụng chúng.

## Tính năng

- Disable các command thông qua config file
- Quản lý danh sách command disable bằng lệnh ingame
- Hệ thống permission linh hoạt
- Bypass permission cho admin
- Tab completion cho các lệnh

## Lệnh

### /dc (hoặc /disablecommand)
- `/dc add <command>` - Thêm command vào danh sách disable
- `/dc remove <command>` - Xóa command khỏi danh sách disable  
- `/dc list` - Xem danh sách command bị disable
- `/dc reload` - Reload config

## Permissions

- `disablecommand.admin` - Quyền sử dụng các lệnh quản trị (default: op)
- `disablecommand.bypass` - Quyền bypass các command bị disable (default: op)

## Cấu hình

File `config.yml`:

```yaml
# Danh sách các command bị disable
disabled-commands:
  - "gamemode"
  - "give" 
  - "tp"

# Thông báo khi command bị disable
disabled-message: "&cCommand này đã bị disable!"

# Thông báo khi không có quyền
no-permission-message: "&cBạn không có quyền sử dụng command này!"

# Bật debug mode
debug: false
```

## Build

```bash
mvn clean package
```

File jar sẽ được tạo trong thư mục `target/`.

## Cài đặt

1. Copy file jar vào thư mục `plugins/` của server
2. Restart server
3. Chỉnh sửa config trong `plugins/DisableCommand/config.yml`
4. Sử dụng `/dc reload` để reload config

## Yêu cầu

- Minecraft 1.21.1
- Paper/Spigot server
- Java 21