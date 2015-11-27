-- Function: atualizarvaloresdeextrato()

-- DROP FUNCTION atualizarvaloresdeextrato();

CREATE OR REPLACE FUNCTION atualizarvaloresdeextrato()
  RETURNS trigger AS
$BODY$
	DECLARE
		valor real;
		diferenca real;
		extrato "Extrato"%rowtype;
	BEGIN
		IF(TG_OP = 'DELETE') THEN
			RAISE NOTICE 'DELETE %',OLD."idExtrato";
			SELECT * INTO extrato FROM "Extrato"
				WHERE "idConta" = OLD."idConta"
				AND "idExtrato" > OLD."idExtrato"
				ORDER BY "idExtrato"
				LIMIT 1;
			valor := OLD."valorInicial";
			diferenca := extrato."valorFinal" - extrato."valorInicial";	
			UPDATE "Extrato"
			SET "valorInicial" = valor, "valorFinal" = valor + diferenca
			WHERE "idExtrato" = extrato."idExtrato";
			return old;
		ELSIF(TG_OP = 'INSERT' OR TG_OP = 'UPDATE') THEN
			RAISE NOTICE 'INSERT OR UPDATE %',NEW."idExtrato";
			valor := NEW."valorFinal";
			FOR extrato IN 
				SELECT * FROM "Extrato"
				WHERE "idConta" = NEW."idConta"
				AND "idExtrato" > NEW."idExtrato"
				ORDER BY "idExtrato"
			LOOP
				diferenca := extrato."valorFinal" - extrato."valorInicial";
				UPDATE "Extrato"
				SET "valorInicial" = valor, "valorFinal" = valor + diferenca
				WHERE "idExtrato" = extrato."idExtrato";
				valor := extrato."valorFinal";
			END LOOP;
			return new;			
		END IF;
	END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION atualizarvaloresdeextrato()
  OWNER TO postgres;

CREATE TRIGGER atualizarvaloresdeextrato
	AFTER INSERT OR UPDATE OR DELETE ON "Extrato"
	FOR EACH ROW
	EXECUTE PROCEDURE atualizarvaloresdeextrato();