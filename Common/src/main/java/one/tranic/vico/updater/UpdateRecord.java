package one.tranic.vico.updater;

import org.jetbrains.annotations.NotNull;

public record UpdateRecord(boolean hasUpdate, @NotNull String newVersion, @NotNull String updateInfo, @NotNull String updateUrl) {
}
