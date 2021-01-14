package Controller;

import Model.Field;
import Networking.FieldDTO;
import Networking.MultiplayerService;

public class MultiplayerController {
    GameController gameController;
    MultiplayerService mpService;

    public MultiplayerController(GameController controller) {
        this.gameController = controller;
    }

    public void setController(GameController controller) {
        this.gameController = controller;
    }

    // call on init
    public void setMpService(MultiplayerService mpService) {
        this.mpService = mpService;
    }

    // call from menuController
    public void setTargetIp(String ipAddress) {
        mpService.setTargetIpAdress(ipAddress);
    }

    // call from gameController
    public void sendEvent(Field field) {
        FieldDTO dto = createFieldDTO(field);
        mpService.sendHttpRequest(dto);
    }

    public FieldDTO createFieldDTO(Field field) {
        return new FieldDTO(
                field.getX(),
                field.getY(),
                field.getState()
        );
    }

    public void receiveEvent(FieldDTO dto) {
        gameController.setFieldState(
                dto.getX(),
                dto.getY(),
                dto.getAction()
        );
    }
}
