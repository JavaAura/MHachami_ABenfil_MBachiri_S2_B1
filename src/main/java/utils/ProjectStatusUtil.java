package utils;

import enums.ProjectStatus;

public class ProjectStatusUtil {
	public static String getBadgeColor(ProjectStatus status) {
		switch (status) {
		case InPreparation:
			return "info";
		case InProgress:
			return "primary";
		case Paused:
			return "warning";
		case Completed:
			return "success";
		case Canceled:
			return "danger";
		default:
			return "default";
		}
	}
}