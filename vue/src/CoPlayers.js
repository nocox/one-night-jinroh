const CoPlayers = class {
  constructor(coPlayers) {
    this.coPlayers = coPlayers;
  }

  find(playerId) {
    const roleName = this.coPlayers.find((co) => co.id === playerId);
    if (roleName) {
      return roleName.role;
    } else {
      return "";
    }
  }
}

export default CoPlayers;