package core;

public class TCCalculator {

	private int level;

	public TCCalculator(int level) {
		this.level = level + 5;
	}

	public String[] getBossTCSuffixes(SpecialMonster boss) {
		String[] ret = new String[3];
		for (int difficulty = 0; difficulty < 3; difficulty++) {
			switch (boss) {
			case SUMMONER:
				ret[difficulty] = this.getSummonerTCSuffix(difficulty, this.level);
				break;
			case RADAMENT:
				ret[difficulty] = this.getRadamentTCSuffix(difficulty, this.level);
				break;
			case GRISWOLD:
				ret[difficulty] = this.getGriswoldTCSuffix(difficulty, this.level);
				break;
			case BLOODRAVEN:
				ret[difficulty] = this.getBloodRavenTCSuffix(difficulty, this.level);
				break;
			case ANDARIEL:
				ret[difficulty] = this.getAndarielTCSuffix(difficulty, this.level);
				break;
			case DURIEL:
				ret[difficulty] = this.getDurielTCSuffix(difficulty, this.level);
				break;
			case MEPHISTO:
				ret[difficulty] = this.getMephistoTCSuffix(difficulty, this.level);
				break;
			case DIABLO:
				ret[difficulty] = this.getDiabloTCSuffix(difficulty, this.level);
				break;
			case IZUAL:
				ret[difficulty] = this.getIzualTCSuffic(difficulty, this.level);
				break;
			default:
				ret[difficulty] = " Desecrated";
				break;
			}
		}
		return ret;
	}

	private String getSummonerTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 32) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 63) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 84) {
				return " Desecrated A";
			}
			if (bosslevel < 87) {
				return " Desecrated B";
			}
			if (bosslevel < 90) {
				return " Desecrated C";
			}
			if (bosslevel < 93) {
				return " Desecrated D";
			}
			if (bosslevel >= 96) {
				return " Desecrated F";
			}
			return " Desecrated E";
		}
		return null;
	}

	private String getRadamentTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 33) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 62) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getGriswoldTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 29) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getIzualTCSuffic(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 38) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 66) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getDiabloTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel >= 48) {
				return " Desecrated B";
			}
			return " Desecrated A";
		case 1:
			if (bosslevel < 67) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel >= 96) {
				return " Desecrated B";
			}
			return " Desecrated A";
		}
		return null;
	}

	private String getMephistoTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 38) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 66) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getDurielTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 36) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 63) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}

	private String getAndarielTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 32) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 78) {
				return " Desecrated A";
			}
			if (bosslevel < 81) {
				return " Desecrated B";
			}
			if (bosslevel < 84) {
				return " Desecrated C";
			}
			if (bosslevel < 87) {
				return " Desecrated D";
			}
			if (bosslevel < 90) {
				return " Desecrated E";
			}
			if (bosslevel < 93) {
				return " Desecrated F";
			}
			if (bosslevel >= 96) {
				return " Desecrated H";
			}
			return " Desecrated G";
		}
		return null;
	}

	private String getBloodRavenTCSuffix(int difficulty, int bosslevel) {
		switch (difficulty) {
		case 0:
			if (bosslevel < 29) {
				return " Desecrated A";
			}
			if (bosslevel >= 48) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 1:
			if (bosslevel < 57) {
				return " Desecrated A";
			}
			if (bosslevel >= 73) {
				return " Desecrated C";
			}
			return " Desecrated B";
		case 2:
			if (bosslevel < 90) {
				return " Desecrated A";
			}
			if (bosslevel < 93) {
				return " Desecrated B";
			}
			if (bosslevel >= 96) {
				return " Desecrated D";
			}
			return " Desecrated C";
		}
		return null;
	}
}
