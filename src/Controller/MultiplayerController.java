package Controller;

import Model.Field;
import Networking.FieldDTO;
import Networking.MultiplayerService;

public class MultiplayerController {
    GameController oppGameController; // opponent game controller
    MultiplayerService mpService;

    public void setGameControllers(GameController _ownGameController, GameController _oppGameController) {
        this.oppGameController = _oppGameController;
        _ownGameController.setMpController(this);
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
                field.getState(),
                field.getTileText());
    }

    public void receiveEvent(FieldDTO dto) {
        oppGameController.updateTile(
                dto.getX(),
                dto.getY(),
                dto.getAction(),
                dto.getTileText()
        );
    }
}
