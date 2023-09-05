package fr.rades.template.infrastructure.SuperviseurFile;

import akka.actor.*;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Settings extends AbstractExtensionId<SettingsImpl>
        implements ExtensionIdProvider {
    public final static Settings Extension = new Settings();
    private Settings() {}
    public Settings lookup() {
        return Settings.Extension;
    }

    public SettingsImpl createExtension(ExtendedActorSystem system) {
        return new SettingsImpl(system);
    }
}

class SettingsImpl implements Extension {

    final FiniteDuration lotDuration;
    final int nombreRequetesLimite;
    final String endPoint;

    final String url;



    public SettingsImpl(ExtendedActorSystem system) {
        nombreRequetesLimite = system.settings().config().getInt("app.createur-lot-requetes.nombre-requetes-limite");
        lotDuration =
                Duration.create(
                        system.settings().config().getDuration(
                                "app.createur-lot-requetes.lot-duration", MILLISECONDS), MILLISECONDS);

        endPoint =
                system.settings().config().getString("app.createur-lot-requetes.action");

        url = system.settings().config().getString("elasticsearch.url");
    }
}

abstract class SettingsActor extends AbstractLoggingActor {
    final SettingsImpl settings = Settings.Extension.get(context().system());
}
