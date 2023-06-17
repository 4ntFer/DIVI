package DTO;

public class RoomDTO {
    private String name;
    private int adminId;

    public RoomDTO(String nome, int adminId) {
        this.name = nome;
        this.adminId = adminId;
    }

    public String getNome() {
        return name;
    }

    public int getAdminId() {
        return adminId;
    }
}
